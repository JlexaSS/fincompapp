package net.fincomp.spring.fincomp.repository;

import javax.servlet.http.HttpServletRequest;

public interface IpRepository {

    String getClientIp(HttpServletRequest request);
}
