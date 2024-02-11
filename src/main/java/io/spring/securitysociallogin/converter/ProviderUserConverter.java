package io.spring.securitysociallogin.converter;

public interface ProviderUserConverter<T, R> {

  R convert(T t);
}
