package by.bsuir.gallery.repository.impl;

import by.bsuir.gallery.model.Photo;
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

    public boolean saveImage(Photo photo) {
        Session session = sessionFactory.getCurrentSession();
        session.save(photo);
        return true;
    }

    @SuppressWarnings("unchecked")
    public List<Photo> allImages() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Photo").list();
    }
}
