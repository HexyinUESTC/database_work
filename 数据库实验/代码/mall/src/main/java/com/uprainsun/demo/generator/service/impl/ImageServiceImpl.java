package com.uprainsun.demo.generator.service.impl;

import com.uprainsun.demo.generator.entity.Image;
import com.uprainsun.demo.generator.mapper.ImageMapper;
import com.uprainsun.demo.generator.service.ImageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author uprainsun
 * @since 2021-03-14
 */
@Service
public class ImageServiceImpl extends ServiceImpl<ImageMapper, Image> implements ImageService {

}
