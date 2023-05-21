package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog

        Blog blog = blogRepository2.findById(blogId).get();
        Image image = new Image();
        image.setDescription(description);
        image.setDimensions(dimensions);
        image.setBlog(blog);

        List<Image> imageList = blog.getImageList();
        imageList.add(image);


        blogRepository2.save(blog);
        return image;

    }

    public void deleteImage(Integer id){
        imageRepository2.deleteById(id);

    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`

        int countImages = 0;
        String [] screenSize= screenDimensions.split("X");
        Image image = imageRepository2.findById(id).get();
        String dimensionOfImage = image.getDimensions();
        String [] imageSize = dimensionOfImage.split("X");

        int imgSidex = Integer.parseInt(imageSize[0]);
        int imgSidey = Integer.parseInt(imageSize[1]);

        int ScreeSizex = Integer.parseInt(screenSize[0]);
        int ScreeSizey = Integer.parseInt(screenSize[1]);
        //4X4 = 4/2*4/2 == 2*2== 4 images
        int sideX = imgSidex/ScreeSizex;
        int sideY = imgSidey/ScreeSizey;

        countImages = sideX*sideY;
        return countImages;

    }
}
