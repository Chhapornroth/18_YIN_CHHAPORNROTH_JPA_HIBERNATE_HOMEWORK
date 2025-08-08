package com.hrd.jpa_hibernate_homework.repository;

import com.hrd.jpa_hibernate_homework.entity.Product;
import com.hrd.jpa_hibernate_homework.exception.NotFoundException;
import com.hrd.jpa_hibernate_homework.model.base.Pagination;
import com.hrd.jpa_hibernate_homework.model.base.PaginationResponseAPI;
import com.hrd.jpa_hibernate_homework.model.request.ProductRequest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class ProductRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Product save(Product newProduct){
        entityManager.persist(newProduct);
        return newProduct;
    }

    public Product findById(Long id) {
        return Optional.ofNullable(entityManager.find(Product.class, id)).orElseThrow(() -> new NotFoundException("product with id: " + id));
    }

    public PaginationResponseAPI<Product> findAll(Integer page, Integer size) {
        List<Product> list = entityManager.createQuery("select p from Product p", Product.class).setFirstResult((page - 1) * size).setMaxResults(size).getResultList();
        int totalItems = entityManager.createQuery("select count(p) from Product p", Long.class).getSingleResult().intValue();
        Pagination pagination = Pagination.builder().totalElements(totalItems).currentPage(page).pageSize(size).totalPages((int) Math.ceil((double) totalItems / size)).build();
        return PaginationResponseAPI.<Product>builder().items(list).pagination(pagination).build();
    }

    public Product save(Long id, ProductRequest request){
        Product optional = Optional.ofNullable(entityManager.find(Product.class, id)).orElseThrow(() -> new NotFoundException("product with id: " + id));
        entityManager.detach(optional);
        optional.setName(request.name());
        optional.setPrice(request.price());
        optional.setQuantity(request.quantity());
        entityManager.merge(optional);
        return optional;
    }

    public void remove(Long id) {
        Product product = findById(id);
        entityManager.remove(product);
    }

    public List<Product> searchByName(String name) {
        return entityManager.createQuery("select p from Product p where p.name ilike :name", Product.class).setParameter("name", "%" + name + "%").getResultList();
    }

    public List<Product> getLowStockProducts(Integer quantity) {
        return entityManager.createQuery("select p from Product p where quantity < : quantity", Product.class).setParameter("quantity", quantity).getResultList();
    }
}
