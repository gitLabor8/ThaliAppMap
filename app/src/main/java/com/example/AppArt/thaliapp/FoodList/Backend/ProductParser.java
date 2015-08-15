package com.example.AppArt.thaliapp.FoodList.Backend;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Generates lists of products per category. This needs to be reworked once a
 * real database is provided.
 *
 * @author Frank Gerlings (s4384873), Lisa Kalse (s4338340), Serena Rietbergen (s4182804)
 */
public class ProductParser {

    private final ProductDummyDb Db = new ProductDummyDb();

    private List<Product> parsedFries = new ArrayList<>();
    private List<Product> parsedPizza = new ArrayList<>();
    private List<Product> parsedSandwich = new ArrayList<>();
    private List<Product> parsedSnacks = new ArrayList<>();

    /**
     * Parses the dummyDB on the category
     * @param cat, the product category
     * @return list of all foods
     */
    public List<Product> getParsed(ProductCategory cat) {
        switch (cat) {
            case PIZZA:
                parsedPizza.clear();
                parsedPizza.addAll(PizzaParsing(Db.pizzaList));
                return parsedPizza;
            case FRIES:
                parsedFries.clear();
                parsedFries.addAll(ProductParsing(Db.friesList, ProductCategory.FRIES));
                return parsedFries;
            case SNACKS:
                parsedSnacks.clear();
                parsedSnacks.addAll(ProductParsing(Db.snackList, ProductCategory.SNACKS));
                return parsedSnacks;
            case SANDWICHES:
                parsedSandwich.clear();
                parsedSandwich.addAll(ProductParsing(Db.sandwichList, ProductCategory.SANDWICHES));
                return parsedSandwich;
            default: return null;
        }
    }

    /**
     * Parses the Sandwich-, Fries- and SnackLists into Product objects
     *
     * @param FSSList Descriptions of fries, sandwiches or snacks
     * @param cat     Category of the given list
     * @return List of Product objects containing all information of FSSList
     */
    public List<Product> ProductParsing(String[] FSSList, ProductCategory cat) {
        // Variables that are about to be filled
        String name;
        Double price;
        List<String> ingredients = new ArrayList<>();

        // Scanning the stringarray and parsing it
        List<Product> newProductList = new ArrayList<>();
        Scanner scan;
        for (String SFSString : FSSList) {
            scan = new Scanner(SFSString);
            scan.useDelimiter("\\n");
            name = scan.next();
            scan.findWithinHorizon("€", 10);
            price = Double.parseDouble(scan.next());

            Product newProduct = new Product(name, price, cat, ingredients);
            ingredients.clear();
            newProductList.add(newProduct);
        }
        return newProductList;
    }

    /**
     * Parses the pizzaString from the dummy database into Product objects
     *
     * @param pizzaList Descriptions of the pizzas
     * @return A list with the new pizzaproducts
     */
    public List<Product> PizzaParsing(String[] pizzaList) {
        // Variables that are about to be filled
        String name;
        Double price;
        List<String> ingredients = new ArrayList<>();

        // Scanning the stringarray and parsing it
        List<Product> newProductList = new ArrayList<>();
        Scanner scan;
        for (String pizzaString : pizzaList) {
            scan = new Scanner(pizzaString);
            scan.useDelimiter("%|, |\\n");
            name = scan.next();
            String ingredient;
            while (scan.hasNext()) {
                ingredient = scan.next();
                ingredients.add(ingredient);
            }
            String priceString = ingredients.get(ingredients.size() - 1);
            ingredients.remove(ingredients.size() - 1);
            scan.close();
            scan = new Scanner(priceString);
            scan.findWithinHorizon("€", 10);
            price = Double.parseDouble(scan.next());

            Product newProduct = new Product(name, price, ProductCategory.PIZZA, ingredients);
            ingredients.clear();
            newProductList.add(newProduct);
        }
        return newProductList;
    }
}

