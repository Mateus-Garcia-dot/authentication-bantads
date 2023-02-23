package com.bantads.authentication.consumer;

import com.bantads.authentication.config.ManagerConfiguration;
import com.bantads.authentication.model.AuthenticationModel;
import com.bantads.authentication.repository.AuthenticationRepository;
import lombok.Data;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@Data
public class AuthenticationConsumer {

    private AuthenticationRepository authenticationRepository;
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "auth.create")
    public void createAuthentication(AuthenticationModel authModel) {
        this.authenticationRepository.save(authModel);
    }

    @RabbitListener(queues = "auth.update")
    public void updateAuthentication(AuthenticationModel authModel) {
        AuthenticationModel auth = this.authenticationRepository.findById(authModel.getUuid()).orElse(null);
        if(auth == null) {
            return;
        }
        auth.setCustomer(authModel.getCustomer());
        auth.setLogin(authModel.getLogin());
        auth.setPassword(authModel.getPassword());
        auth.setType(authModel.getType());
        auth.setIsApproved(authModel.getIsApproved());
        auth.setIsPending(authModel.getIsPending());
    }

    @RabbitListener(queues = "auth.delete")
    public void deleteAuthentication(AuthenticationModel authenticationModel) {
        this.authenticationRepository.deleteById(authenticationModel.getUuid());
    }

    @RabbitListener(queues = "auth.patch")
    public void patchAuthentication(AuthenticationModel authModel) {
        AuthenticationModel auth = this.authenticationRepository.findByCustomer(authModel.getCustomer());
        if(auth == null) {
            return;
        }
        if(authModel.getCustomer() != null) {
            auth.setCustomer(authModel.getCustomer());
        }
        if(authModel.getLogin() != null) {
            auth.setLogin(authModel.getLogin());
        }
        if(authModel.getPassword() != null) {
            auth.setPassword(authModel.getPassword());
        }
        if(authModel.getType() != null) {
            auth.setType(authModel.getType());
        }
        if(authModel.getIsApproved() != null) {
            auth.setIsApproved(authModel.getIsApproved());
        }
        if(authModel.getIsPending() != null) {
            auth.setIsPending(authModel.getIsPending());
        }
        rabbitTemplate.convertAndSend(ManagerConfiguration.sortRequestQueueName, 1);
        this.authenticationRepository.save(auth);
    }

}
