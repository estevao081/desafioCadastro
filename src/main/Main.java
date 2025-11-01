package main;

import main.services.MenuService;

public class Main {
    public static void main(String[] args) {

        MenuService menuService = new MenuService();

        menuService.exibirMenu();

    }
}