package io.vinson.web.svc.controller;

import io.vinson.web.svc.service.UserManager;
import io.vinson.web.svc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;


@Controller
@RequestMapping("/disk")
public class DiskController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserManager userManager;

    @GetMapping(value = {"", "/index"})
    public String disk(Model model) {

        return "/disk";
    }

    @GetMapping("get_root_dir")
    @ResponseBody
    public Map<String, Object> getRootDir() {
        int userId = userManager.getUserId();

        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("code", 0);
        return jsonMap;
    }

    @PostMapping("mkdir")
    @ResponseBody
    public Map<String, Object> mkdir(@RequestParam("parentDirId") int parentDirId, @RequestParam("name") String name) {
        //验证是否登录
        int userId = userManager.getUserId();
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("code", 0);
        return jsonMap;
    }

    @GetMapping("/come_dir")
    @ResponseBody
    public Map<String, Object> comeDir(@RequestParam("dirId") int dirId, HttpServletRequest request, HttpServletResponse response) {

        Map<String, Object> jsonMap = new HashMap<>();
        return jsonMap;
    }

    @GetMapping("/get_dir")
    @ResponseBody
    public Map<String, Object> getDir(@RequestParam("id") int dirId) {

        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("code", 0);
        return jsonMap;
    }


    @DeleteMapping("delete_dir")
    @ResponseBody
    public void deleteDir(@RequestParam("id") int dirId) {
    }
    @DeleteMapping("delete_file")
    @ResponseBody
    public void deleteFile(@RequestParam("id") int id) {
    }

    @PostMapping("copy_dir")
    public void copyDir(@RequestParam("dirId") int dirId, @RequestParam("desDirId") int desDirId) {
    }

    @PostMapping("copy_file")
    public void copyFile(@RequestParam("fileId") int fileId, @RequestParam("desDirId") int desDirId) {

    }

    @PutMapping("rename_dir")
    @ResponseBody
    public Map<String, Object> renameDir(@RequestParam("id") int dirId, @RequestParam("name") String name) {
        Date now = new Date();
        int userId = userManager.getUserId();

        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("code", 0);
        jsonMap.put("name", name);
        return jsonMap;
    }

    @PutMapping("rename_file")
    @ResponseBody
    public Map<String, Object> renameFile(@RequestParam("id") int fileId, @RequestParam("name") String name) {
        Date now = new Date();
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("code", 0);
        jsonMap.put("name", name);
        return jsonMap;
    }

    @PostMapping("/upload")
    @ResponseBody
    public Map<String, Object> upload(@RequestParam("directoryId") int directoryId, HttpServletRequest request) {

        Date now = new Date();
        Map<String, Object> jsonMap = new HashMap<>();

        jsonMap.put("code", 0);
        return jsonMap;
    }

    @PostMapping("/move")
    @ResponseBody
    public Map<String, Object> move(@RequestParam("parentId") int parentId,
                                    @RequestParam(value = "fileIds[]", required = false) int[] fileIds,
                                    @RequestParam(value = "dirIds[]", required = false) int[] dirIds) {


        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("code", 0);
        return jsonMap;
    }

    @PostMapping("/share")
    @ResponseBody
    public Map<String, Object> share(@RequestParam(value = "fileIds[]", required = false) int[] fileIds,
                                     @RequestParam(value = "dirIds[]", required = false) int[] dirIds,
                                     @RequestParam(value = "method") String method,
                                     @RequestParam("day") int day) {
        Date now = new Date();
        Map<String, Object> jsonMap = new HashMap<>();

        return jsonMap;
    }

}
