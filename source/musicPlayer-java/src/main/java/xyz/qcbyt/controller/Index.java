package xyz.qcbyt.controller;


import org.springframework.web.bind.annotation.*;
import xyz.qcbyt.common.lang.Result;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 青菜白玉汤
 * @since 2022-05-22
 */
@RestController
@RequestMapping("/index")
@CrossOrigin
public class Index {

    @GetMapping("/hello/{name}")
    public Result hello(@PathVariable("name") String name){
        return Result.succ("你好！ "+name);
    }

}
