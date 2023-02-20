package com.bantads.authentication.consumer;

import com.bantads.authentication.model.AuthenticationModel;
import com.bantads.authentication.repository.AuthenticationRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Data
public class AuthenticationConsumer {

    private AuthenticationRepository authenticationRepository;

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

}
