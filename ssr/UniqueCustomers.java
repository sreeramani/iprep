package com.ssr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by ramanis on 3/12/17.
 */
public class UniqueCustomers {

    static class CustomerPurchase {
        public CustomerPurchase(String customerId, String productCategory) {
            CustomerId = customerId;
            ProductCategory = productCategory;
        }

        public String getCustomerId() {
            return CustomerId;
        }

        public void setCustomerId(String customerId) {
            CustomerId = customerId;
        }

        public String getProductCategory() {
            return ProductCategory;
        }

        public void setProductCategory(String productCategory) {
            ProductCategory = productCategory;
        }

        String CustomerId;
        String ProductCategory;
    }

    public static void main(String args[]) {
        List<CustomerPurchase> purchases = new ArrayList<>();
        purchases.add(new CustomerPurchase("Bob8472","Books"));
        purchases.add(new CustomerPurchase("Nikhil555","Pets"));
        purchases.add(new CustomerPurchase("Bob8472","Movies"));
        purchases.add(new CustomerPurchase("Keisha9001","Pets"));
        purchases.add(new CustomerPurchase("Nikhil555","Pets"));
        purchases.add(new CustomerPurchase("Isabella369","Pets"));
        purchases.add(new CustomerPurchase("Isabella369","Books"));
        purchases.add(new CustomerPurchase("Zhang206","Books"));
        System.out.println(countExclusiveCustomers2(purchases));
    }

    public static int countExclusiveCustomers(List<CustomerPurchase> customerPurchases) {
        HashMap<String, String> map = new HashMap<String, String>();
        HashMap<String, Integer> count = new HashMap<String, Integer>();
        // Iterate through each element of the list
        for (CustomerPurchase c : customerPurchases) {
            String id = c.getCustomerId();
            String cat = c.getProductCategory();
            if (!map.containsKey(id)) {
                map.put(id, cat);
                // If the category is not in the table yet, add it
                if (!count.containsKey(cat))
                {
                    count.put(cat, 1);
                }
                else // If the category is already in the table, add 1 to the number of its customers.
                {
                    count.put(cat, count.get(cat) + 1);
                }
            }
            else {
                if (!map.get(id).equals("REMOVED"))
                {
                    // This customer is NOT an isolated customer, so he/she should not be counted any longer.
                    if (!map.get(id).equals(cat)) {
                        count.put(map.get(id), count.get(map.get(id)) - 1);
                        map.put(id, "REMOVED");
                    }
                }
            }
        }
        // Get the maximum value out of the counting table.
        int max = -1;
        Iterator iter = count.entrySet().iterator();
        /**for (Map.Entry entry:count.entrySet()) {
            if ((int) entry.getValue() > max) {
                max = (int)entry.getValue();
            }
        }**/
        while (iter.hasNext()) {
            Map.Entry pair = (Map.Entry)iter.next();
            if ((int)pair.getValue() > max) {
                max = (int)pair.getValue();
            }
            iter.remove();
        }
        return max;
    }

    static int countExclusiveCustomers2(List<CustomerPurchase> customerPurchases) {
        if (customerPurchases.size() < 1)
            return 0;
        Map<String, Integer> userProductsCount = new HashMap<String, Integer>();
        for (CustomerPurchase customerPurchase : customerPurchases) {
            String name = customerPurchase.getCustomerId();
            if (userProductsCount.containsKey(name)) {
                int value = userProductsCount.get(name);
                userProductsCount.put(name, ++value);
            } else {
                userProductsCount.put(name, 1);
            }
        }
        int max = 0;
        Map<String, Integer> isolatedProductCounts = new HashMap<String, Integer>();
        for (CustomerPurchase customerPurchase : customerPurchases) {
            String name = customerPurchase.getCustomerId();
            if (userProductsCount.get(name) == 1) {
                String productId = customerPurchase.getProductCategory();
                if (isolatedProductCounts.containsKey(productId)) {
                    int value = isolatedProductCounts.get(productId);
                    isolatedProductCounts.put(productId, ++value);
                } else {
                    isolatedProductCounts.put(productId, 1);
                }
            }
        }
        for (String category : isolatedProductCounts.keySet()) {
            if (max < isolatedProductCounts.get(category)) {
                max = isolatedProductCounts.get(category);
            }
        }
        return max;
    }
}
