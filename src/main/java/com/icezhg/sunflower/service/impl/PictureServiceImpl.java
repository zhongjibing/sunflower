package com.icezhg.sunflower.service.impl;

import com.icezhg.commons.exception.ErrorCodeException;
import com.icezhg.commons.util.IdGenerator;
import com.icezhg.sunflower.common.Constant;
import com.icezhg.sunflower.dao.BinaryDataDao;
import com.icezhg.sunflower.dao.PictureDao;
import com.icezhg.sunflower.domain.BinaryData;
import com.icezhg.sunflower.domain.Picture;
import com.icezhg.sunflower.pojo.query.NameQuery;
import com.icezhg.sunflower.service.PictureService;
import com.icezhg.sunflower.util.SecurityUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by zhongjibing on 2022/09/11.
 */
@Service
public class PictureServiceImpl implements PictureService {

    private final PictureDao pictureDao;

    private final BinaryDataDao binaryDataDao;

    public PictureServiceImpl(PictureDao pictureDao, BinaryDataDao binaryDataDao) {
        this.pictureDao = pictureDao;
        this.binaryDataDao = binaryDataDao;
    }

    @Override
    public Picture findById(String id) {
        return pictureDao.findById(id);
    }

    @Override
    public int deleteById(String id) {
        return pictureDao.deleteById(id);
    }

    @Override
    public Picture save(MultipartFile file) {
        BinaryData binaryData = saveImage(file);

        Picture picture = new Picture();
        picture.setId(IdGenerator.nextId());
        picture.setName(truncFileName(file.getOriginalFilename()));
        picture.setType(file.getContentType());
        picture.setMd5(binaryData.getMd5());

        picture.setCreateBy(SecurityUtil.currentUserName());
        picture.setCreateTime(new Date());
        picture.setUpdateBy(SecurityUtil.currentUserName());
        picture.setUpdateTime(new Date());
        pictureDao.insert(picture);
        return picture;
    }

    private String truncFileName(String filename) {
        String name = FilenameUtils.getName(FilenameUtils.normalize(filename));
        if (name.length() > Constant.MAX_UPLOAD_FILENAME_LEN) {
            String baseName = FilenameUtils.getBaseName(name);
            String extension = FilenameUtils.getExtension(name);
            int len = Constant.MAX_UPLOAD_FILENAME_LEN - extension.length() - 2;
            return StringUtils.substring(baseName, 0, len) + "%." + extension;
        }
        return name;
    }

    private BinaryData saveImage(MultipartFile file) {
        BinaryData binaryData = new BinaryData();
        try {
            byte[] data = file.getBytes();
            binaryData.setData(data);
            binaryData.setMd5(DigestUtils.md5Hex(data));
        } catch (IOException e) {
            throw new ErrorCodeException("", "read picture data error");
        }
        BinaryData existing = binaryDataDao.findByMd5(binaryData.getMd5());
        if (existing != null) {
            return existing;
        }

        binaryDataDao.insert(binaryData);
        return binaryData;
    }

    @Override
    public Picture update(Picture record) {
        pictureDao.update(record);
        return pictureDao.findById(record.getId());
    }

    @Override
    public int count(NameQuery query) {
        return pictureDao.count(query.toMap());
    }

    @Override
    public List<Picture> find(NameQuery query) {
        return pictureDao.find(query.toMap());
    }

    @Override
    public Picture findByAvatar(String avatar) {
        return pictureDao.findByAvatar(avatar);
    }

}
