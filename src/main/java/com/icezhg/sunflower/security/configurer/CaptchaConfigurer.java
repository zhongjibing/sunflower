package com.icezhg.sunflower.security.configurer;

import com.icezhg.captcha.CaptchaProducer;
import com.icezhg.captcha.Config;
import com.icezhg.captcha.impl.DefaultCaptchaProducer;
import com.icezhg.captcha.impl.ShadowGimpy;
import com.icezhg.captcha.noise.DefaultNoise;
import com.icezhg.captcha.text.DelegateTextProducer;
import com.icezhg.captcha.text.TextProducer;
import com.icezhg.sunflower.security.captcha.CaptchaFilter;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.awt.Color;
import java.awt.Font;

/**
 * Created by zhongjibing on 2022/09/02.
 */
public class CaptchaConfigurer<B extends HttpSecurityBuilder<B>> extends AbstractHttpConfigurer<CaptchaConfigurer<B>, B> {

    private String loginProcessingUrl = "/login";
    private String captchaUrl = "/captcha";

    @Override
    public void init(B http) throws Exception {
    }

    @Override
    public void configure(B http) throws Exception {
        CaptchaProducer captchaProducer = http.getSharedObject(CaptchaProducer.class);
        if (captchaProducer == null) {
            captchaProducer = getCaptchaProducer(http);
        }
        CaptchaFilter filter = new CaptchaFilter(captchaProducer);
        http.addFilterBefore(postProcess(filter), UsernamePasswordAuthenticationFilter.class);
    }

    private CaptchaProducer getCaptchaProducer(B http) {
        TextProducer textProducer = http.getSharedObject(TextProducer.class);
        if (textProducer == null) {
            textProducer = new DelegateTextProducer();
        }
        Config config = http.getSharedObject(Config.class);
        if (config == null) {
            config = defaultConfig();
        }
        return new DefaultCaptchaProducer(textProducer, config);
    }

    private Config defaultConfig() {
        Config config = new Config();
        config.setWidth(220);
        config.setHeight(60);
        config.setBorderColor(new Color(105, 179, 90));
        config.setTextFontColor(Color.BLUE);
        Font[] fonts = {new Font("Arial", Font.BOLD, 36), new Font("Courier", Font.BOLD, 36)};
        config.setTextFont(fonts);
        config.setTextCharSpace(6);
        config.setNoiseProducer(new DefaultNoise());
        config.setNoiseColor(Color.BLUE);
        config.setGimpyEngine(new ShadowGimpy());
        return config;
    }

}
