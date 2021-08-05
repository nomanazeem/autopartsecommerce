package nazeem.autoparts.library.service;

/*
    Created By: noman azeem
    Contact: syed.noman.azeem@gmail.com
*/
import nazeem.autoparts.library.model.Product;
import nazeem.autoparts.library.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }
    public List<Product> findAllByActive() {
        return productRepository.findAllByActive();
    }
    public Page<Product> findAllByCategoryId(Long categoryId, Pageable pageable) {
        List<Product> products = productRepository.findAllByCategoryId(categoryId);

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Product> list;

        if (products.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, products.size());
            list = products.subList(startItem, toIndex);
        }

        Page<Product> pageProducts = new PageImpl<Product>(list, PageRequest.of(currentPage, pageSize), products.size());

        return pageProducts;
    }

    public List<Product> topMostOrderedProducts(Integer top) {
        return productRepository.topMostOrderedProducts(top);
    }


    public Page<Product> searchResults(String keyword, String categoryId, String makeId, String modelId, String year, Pageable pageable) {
        List<Product> products = productRepository.searchProduct2(keyword, categoryId, makeId, modelId, year);
        //return products;

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Product> list;

        if (products.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, products.size());
            list = products.subList(startItem, toIndex);
        }

        Page<Product> pageProducts = new PageImpl<Product>(list, PageRequest.of(currentPage, pageSize), products.size());

        return pageProducts;
    }

    public void save(Product product) {
        productRepository.save(product);

        if(product.getImage_posted1().getSize() > 0 ||
                product.getImage_posted2().getSize() > 0 ||
                product.getImage_posted3().getSize() > 0 ||
                product.getImage_posted4().getSize() > 0
        ) {
            if(product.getImage_posted1().getSize() > 0 ) {
                String image1 = ImageUpload(product.getId(), product.getImage_posted1());
                product.setImage1(image1);
            }

            if(product.getImage_posted2().getSize() > 0 ) {
                String image2 = ImageUpload(product.getId(), product.getImage_posted2());
                product.setImage2(image2);
            }

            if(product.getImage_posted3().getSize() > 0 ) {
                String image3 = ImageUpload(product.getId(), product.getImage_posted3());
                product.setImage3(image3);
            }

            if(product.getImage_posted4().getSize() > 0 ) {
                String image4 = ImageUpload(product.getId(), product.getImage_posted4());
                product.setImage4(image4);
            }

            productRepository.save(product);
        }
    }
    private String ImageUpload(Long productId, MultipartFile productImage1){
        String fileName="";

        String productFolder = "admin/src/main/resources/static/upload/product";
        //Save image
        try {
            byte[] bytes = productImage1.getBytes();

            //Create directory if not exists
            File file = new File(productFolder+"/"+productId);
            if(!file.exists()){
                file.mkdirs();
            }

            fileName = productImage1.getName()+".png";

            String fileWithFolderName = productFolder+"/"+ productId +"/"+ fileName;

            BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(
                    new File(fileWithFolderName)));

            stream.write(bytes);
            stream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return fileName;
    }

    public Product get(long id) {
        return productRepository.findById(id).get();
    }

    public void delete(long id) {
        productRepository.deleteById(id);
    }
}