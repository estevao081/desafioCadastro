package main;

import main.services.MenuService;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {

        MenuService menuService = new MenuService();

        menuService.exibirMenu();

    }
}