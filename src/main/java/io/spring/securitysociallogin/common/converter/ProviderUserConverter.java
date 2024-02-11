package io.spring.securitysociallogin.common.converter;

public interface ProviderUserConverter<T, R> {

  R convert(T t);
}
