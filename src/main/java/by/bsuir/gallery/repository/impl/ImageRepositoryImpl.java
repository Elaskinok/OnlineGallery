package by.bsuir.gallery.repository.impl;

import by.bsuir.gallery.model.Image;
import by.bsuir.gallery.repository.ImageRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ImageRepositoryImpl implements ImageRepository {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public boolean saveImage(Image image) {
        Session session = sessionFactory.getCurrentSession();
        session.save(image);
        return true;
    }

    @SuppressWarnings("unchecked")
    public List<Image> allImages() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Image").list();
    }
}
