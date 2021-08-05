package nazeem.autoparts.library.service;

/*
    Created By: noman azeem
    Contact: syed.noman.azeem@gmail.com
*/
import nazeem.autoparts.library.model.CartItem;
import nazeem.autoparts.library.model.Customer;
import nazeem.autoparts.library.model.Product;
import nazeem.autoparts.library.model.ShoppingCart;
import nazeem.autoparts.library.repository.CartItemRepository;
import nazeem.autoparts.library.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    private Float TAX_RATE =5.0F; //5% Percent

    public ShoppingCart findById(Long shoppingCartId){
        return shoppingCartRepository.findById(shoppingCartId).get();
    }

    private CartItem findCartItem(ShoppingCart shoppingCart, Long productId){

        System.out.println("Cart item count"+shoppingCart.getCartItemList().stream().count());

        for (CartItem cartItem : shoppingCart.getCartItemList()) {
            //item found
            if (cartItem.getProduct().getId().equals(productId)) {
                return cartItem;
            }
        }
        return null;
    }
    private Float getSubTotal(ShoppingCart shoppingCart){
        Float subTotal=0.0F;
        for (CartItem cartItem : shoppingCart.getCartItemList()) {
            subTotal += cartItem.getTotalPrice();
        }
        return subTotal;
    }
    public ShoppingCart findShoppingCart(Customer customer){
        if(customer.getShoppingCart() == null){
            ShoppingCart shoppingCart = new ShoppingCart();

            shoppingCart.setPaymentMethod("Cash On Delivery");
            shoppingCart.setShippingMethod("Free Shipping");

            shoppingCart.setCustomer(customer);
            shoppingCart.setCartItemList(new ArrayList<CartItem>());

            return shoppingCart;
        }
        return customer.getShoppingCart();
    }

    public ShoppingCart addItemToCart(Product product, Long quantity, Customer customer) {
        Float subTotal = 0.0F, totalPrice = 0.0F, totalTax = 0.0F, grandTotal = 0.0F;
        long totalQty = 0;

        ShoppingCart shoppingCart = findShoppingCart(customer);
        CartItem cartItem = findCartItem(shoppingCart, product.getId());
        if(cartItem == null){
            cartItem = new CartItem();

            cartItem.setProduct(product);
            cartItem.setShoppingCart(shoppingCart);

            //get items list and item in it
            List<CartItem> cartItemList = shoppingCart.getCartItemList();
            cartItemList.add(cartItem);

            shoppingCart.setCartItemList(cartItemList);
        }

        cartItem.setOurPrice(product.getOurPrice());

        //quantity
        totalQty = cartItem.getQuantity() + quantity;
        cartItem.setQuantity(totalQty);

        //total
        totalPrice = product.getOurPrice() * totalQty;
        cartItem.setTotalPrice(totalPrice);


        //-------------------//
        shoppingCart.setShippingTotal(0.0F);

        //sub total
        subTotal = getSubTotal(shoppingCart);
        shoppingCart.setSubTotal(subTotal);

        //Tax rate
        shoppingCart.setTaxRate(TAX_RATE);

        //total Tax
        totalTax = (TAX_RATE / 100) * subTotal;
        shoppingCart.setTaxTotal(totalTax);

        //grand total
        grandTotal = subTotal + totalTax;
        shoppingCart.setGrandTotal(grandTotal);

        shoppingCartRepository.save(shoppingCart);

        return shoppingCart;
    }
    public ShoppingCart removeItemFromCart(Product product, Customer customer){
        Float subTotal = 0.0F, totalPrice = 0.0F, totalTax = 0.0F, grandTotal = 0.0F;
        long totalQty = 0;

        ShoppingCart shoppingCart = findShoppingCart(customer);
        CartItem cartItem = findCartItem(shoppingCart, product.getId());

        System.out.println("cart item="+cartItem);

        List<CartItem> cartItemList = shoppingCart.getCartItemList();
        System.out.println("before size="+cartItemList.stream().count());

        cartItemList.remove(cartItem);

        System.out.println("after size="+cartItemList.stream().count());

        //set updated cart item
        shoppingCart.setCartItemList(cartItemList);

        //-------------------//
        shoppingCart.setShippingTotal(0.0F);

        //sub total
        subTotal = getSubTotal(shoppingCart);
        shoppingCart.setSubTotal(subTotal);

        //Tax rate
        shoppingCart.setTaxRate(TAX_RATE);

        //total Tax
        totalTax = (TAX_RATE / 100) * subTotal;
        shoppingCart.setTaxTotal(totalTax);

        //grand total
        grandTotal = subTotal + totalTax;
        shoppingCart.setGrandTotal(grandTotal);

        shoppingCartRepository.save(shoppingCart);

        //deleted instance passed to merge:

        //Delete child item
        cartItemRepository.delete(cartItem);

        return shoppingCart;
    }

    public ShoppingCart updateItemFromCart(Product product, Long quantity, Customer customer){
        Float subTotal = 0.0F, totalPrice = 0.0F, totalTax = 0.0F, grandTotal = 0.0F;
        long totalQty = 0;

        ShoppingCart shoppingCart = findShoppingCart(customer);
        CartItem cartItem = findCartItem(shoppingCart, product.getId());

        cartItem.setOurPrice(product.getOurPrice());

        //quantity
        totalQty = quantity;
        cartItem.setQuantity(totalQty);

        //total
        totalPrice = product.getOurPrice() * totalQty;
        cartItem.setTotalPrice(totalPrice);


        //-------------------//
        shoppingCart.setShippingTotal(0.0F);

        //sub total
        subTotal = getSubTotal(shoppingCart);
        shoppingCart.setSubTotal(subTotal);

        //Tax rate
        shoppingCart.setTaxRate(TAX_RATE);

        //total Tax
        totalTax = (TAX_RATE / 100) * subTotal;
        shoppingCart.setTaxTotal(totalTax);

        //grand total
        grandTotal = subTotal + totalTax;
        shoppingCart.setGrandTotal(grandTotal);

        shoppingCartRepository.save(shoppingCart);

        return shoppingCart;
    }


    public void emptyShoppingCart(Customer customer) {
        if(customer == null || customer.getShoppingCart()==null) return;

        shoppingCartRepository.delete(customer.getShoppingCart());
    }

}