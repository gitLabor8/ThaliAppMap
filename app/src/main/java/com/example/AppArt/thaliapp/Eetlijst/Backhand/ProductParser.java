package com.example.AppArt.thaliapp.Eetlijst.Backhand;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Generates a list of products. This needs to be reworked once a real database
 * is provided.
 *
 * @author Frank Gerlings (s4384873), Lisa Kalse (s4338340), Serena Rietbergen
 *         (s4182804)
 */

public class ProductParser {

    private final ProductDummyDb Db = new ProductDummyDb();
    // Absolutely maximum amount of foodkinds that could be within the database
    private final int bigConstant = 100;
    private List<Product> parsedProducts = new ArrayList<>();
    private List<Product> parsedFries = new ArrayList<>();
    private List<Product> parsedPizza = new ArrayList<>();
    private List<Product> parsedSandwich = new ArrayList<>();
    private List<Product> parsedSnacks = new ArrayList<>();

    /**
     * Parses a productdatabase to a list of products
     *
     * @return A list of the parsed products.
     */
    public void Parsing() {
        parsedProducts.addAll(ProductParsing(Db.friesList, ProductCategory.FRIES));
        parsedFries.addAll(ProductParsing(Db.friesList, ProductCategory.FRIES));
        parsedProducts.addAll(PizzaParsing(Db.pizzaList));
        parsedPizza.addAll(PizzaParsing(Db.pizzaList));
        parsedProducts.addAll(ProductParsing(Db.sandwichList, ProductCategory.SANDWICHES));
        parsedSandwich.addAll(ProductParsing(Db.sandwichList, ProductCategory.SANDWICHES));
        parsedProducts.addAll(ProductParsing(Db.snackList, ProductCategory.SNACKS));
        parsedSnacks.addAll(ProductParsing(Db.snackList, ProductCategory.SNACKS));
    }

    public List<Product> FriesParsing() {
        return parsedFries;
    }

    public List<Product> PizzaParsing() {
        return parsedPizza;
    }

    public List<Product> SandwichParsing() {
        return parsedSandwich;
    }

    public List<Product> SnackParsing() {
        return parsedSnacks;
    }

    /**
     * Parses the Sandwich-, Fries- and SnackLists into Product objects
     *
     * @param SFSList
     * @param cat
     * @return
     */
    public List<Product> ProductParsing(String[] SFSList, ProductCategory cat) {
        // Variables that are about to be filled
        String name;
        Double price;
        List<String> ingredients = new ArrayList<>();

        // Scanning the stringarray and parsing it
        List<Product> newProductList = new ArrayList<>();
        Scanner scan;
        for (String SFSString : SFSList) {
            scan = new Scanner(SFSString);
            scan.useDelimiter("\\n");
            name = scan.next();
            scan.findWithinHorizon("€", 10);
            price = scan.nextDouble();

            Product newProduct = new Product(name, price, cat, ingredients);
            ingredients.clear();
            newProductList.add(newProduct);
        }
        return newProductList;
    }

    /**
     * Parses the pizzaString from the dummy database into Product objects
     *
     * @param pizzaList
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
            price = scan.nextDouble();

            Product newProduct = new Product(name, price, ProductCategory.PIZZA, ingredients);
            ingredients.clear();
            newProductList.add(newProduct);
        }
        return newProductList;
    }
}

