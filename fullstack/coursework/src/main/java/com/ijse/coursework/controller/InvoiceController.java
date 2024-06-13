package com.ijse.coursework.controller;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.ijse.coursework.invoice.GeneratePdf;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@CrossOrigin(origins = "*")

public class InvoiceController {

    @Autowired
    private GeneratePdf generatePdf;

    @PostMapping("/invoice/generate/user/{userId}/order/{orderId}")
    public String getMethodName(@PathVariable Long userId, @PathVariable Long orderId) throws FileNotFoundException, MalformedURLException {
        System.out.println("OOOOOOOOOOOOOOOOOOOOOOOKKKKKKKKKKKKKK");
        generatePdf.pdfGenerate(userId, orderId);
        return "call PDF";
    }
}