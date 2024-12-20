package org.example.service.impl;

import org.example.repository.impl.AdminRepositoryImpl;
import org.example.service.AdminService;

public class AdminServiceImpl implements AdminService {
    AdminRepositoryImpl adminRepositoryImpl = new AdminRepositoryImpl();
}
