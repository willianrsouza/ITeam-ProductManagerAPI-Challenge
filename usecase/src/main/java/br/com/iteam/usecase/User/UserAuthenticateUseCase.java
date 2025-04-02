package br.com.iteam.usecase.User;

public interface UserAuthenticateUseCase {
    Boolean authenticate(String email, String password);
}
