package com.hongseokandrewjang.android.firebase_database;

import java.util.ArrayList;

/**
 * Created by HongSeokAndrewJang on 2016-10-29.
 */

public class ChickenStore {
    String name;
    String branch;
    Long delivery_fee;
    String logo;
    ArrayList<Menu> menus = new ArrayList<>();

    public ChickenStore(String name, String branch, Long delivery_fee, String logo, ArrayList<Menu> menus) {
        this.name = name;
        this.branch = branch;
        this.delivery_fee = delivery_fee;
        this.logo = logo;
        this.menus = menus;
    }

    public ChickenStore() {
    }

    public class Menu{
        public Menu(String menu_name) {
            this.menu_name = menu_name;
        }

        String menu_name;
        String menu_image;
        Long menu_price;

        public String getMenu_name() {
            return menu_name;
        }

        public String getMenu_image() {
            return menu_image;
        }

        public Long getMenu_price() {
            return menu_price;
        }

        public Menu() {
        }
    }
}
