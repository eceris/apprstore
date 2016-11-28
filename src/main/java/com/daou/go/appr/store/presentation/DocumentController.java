package com.daou.go.appr.store.presentation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.daou.go.appr.store.component.category.model.Category;
import com.daou.go.appr.store.component.document.DocumentService;
import com.daou.go.appr.store.component.document.model.Document;
import com.daou.go.appr.store.component.tag.model.Tag;

import gui.ava.html.image.generator.HtmlImageGenerator;

@Controller
public class DocumentController {

    private static final Logger logger = LoggerFactory.getLogger(DocumentController.class);

    @Value("${apprstore.thumbnail.path}")
    private String thumbnailPath;

    @Autowired
    private DocumentService documentService;

    @RequestMapping("/documentRegister")
    public String goDocumentRegister() {
        return "documentRegister";
    }

    @RequestMapping("/documentList")
    public String goDocumentList() {
        return "documentList";
    }

    @RequestMapping(value = "/document/create", method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody Document document) {

        logger.info("/Creating Document : ", document.getName());

        try {
            // 썸네일 이미지 생성
            String fileFullPath = convertHtmlToImage(document.getHtml(), document.getName(), document.getId());
            document.setThumbNailImage(fileFullPath);

            documentService.save(document);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping("/document/read/{id}")
    @ResponseBody
    public Document read(@PathVariable String id) {
        return documentService.get(id);
    }

    @RequestMapping(value = "/document/list", method = RequestMethod.POST)
    public ResponseEntity<List<Document>> selectDocuments(@RequestBody Document document) {

        List<Document> lists = new ArrayList<>();

        Category category = document.getCategory();
        String categoryId = category.getId();
        List<Tag> tags = document.getTags();

        try {
            if (category != null && tags != null) {
                //루트노드
                if ("root".equals(categoryId)) {
                    if (tags.size() == 0) {
                        lists = documentService.findAll();
                    } else {
                        lists = documentService.findByTags(tags);
                    }
                    //루트 이하 자식노드(카테고리 선택)
                } else {
                    if (tags.size() == 0) {
                        lists = documentService.findByCategory(category);
                    } else {
                        lists = documentService.findByCategoryAndTags(category, tags);
                    }
                }
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return new ResponseEntity<List<Document>>(lists, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/getImage", method = RequestMethod.GET)
    public void getImage(String path, HttpServletResponse re) throws IOException {

        logger.info("/getImage : {}", path);
        OutputStream out = null;
        InputStream in = null;

        try {
            out = re.getOutputStream();
            in = new FileInputStream(path);
            IOUtils.copy(in, out);
        } finally {
            IOUtils.closeQuietly(in);
            IOUtils.closeQuietly(out);
        }
    }

    /**
     * API Call (결재양식 All Select)
     */
    @RequestMapping(value = "/api/selectDocuments", method = RequestMethod.GET)
    public ResponseEntity<List<Document>> apiSelectDocuments() {
        List<Document> lists = new ArrayList<>();
        try {
            lists = documentService.findAll();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return new ResponseEntity<List<Document>>(lists, HttpStatus.CREATED);
    }

    /**
     * Convert Html to Image
     * 
     * @throws IOException
     */
    public String convertHtmlToImage(String html, String name, String id) throws IOException {

        // Generator 생성
        HtmlImageGenerator htmlImageGenerator = new HtmlImageGenerator();
        htmlImageGenerator.loadHtml(html);

        long currentTime = System.currentTimeMillis();

        Path thumbnailPaths = Paths.get(thumbnailPath);
        if (!Files.exists(thumbnailPaths, LinkOption.NOFOLLOW_LINKS)) {
            Files.createDirectories(thumbnailPaths);
        }

        String fileFullPath = thumbnailPath + name + '_' + String.valueOf(currentTime) + ".png";
        File file = new File(fileFullPath);
        htmlImageGenerator.saveAsImage(file);

        return fileFullPath;
    }

}
