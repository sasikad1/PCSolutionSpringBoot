package com.ijse.coursework.controller;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.ijse.coursework.invoice.GeneratePdf;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@CrossOrigin(origins = "*")

public class InvoiceController {

    @Autowired
    private GeneratePdf generatePdf;

    @RequestMapping("/invoice/generate")
    public String getMethodName() throws FileNotFoundException, MalformedURLException {
        generatePdf.pdfGenerate();
        return "call PDF";
    }
}