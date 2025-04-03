package br.com.iteam.usecase.User;

import br.com.iteam.core.domain.entity.User;

public interface UserAuthenticateUseCase {
    User authenticate(String email, String password);
}
