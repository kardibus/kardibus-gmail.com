package com.example.triton.controller;

import com.example.triton.domain.Device;
import com.example.triton.domain.ListDevice;
import com.example.triton.repository.DeviceRepo;
import com.example.triton.repository.ListDeviceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
public class ListDevController {

    @Autowired
    private DeviceRepo deviceRepo;
    @Autowired
    private ListDeviceRepo listDeviceRepo;

    @GetMapping("/listdev")
    public String lisdev(Model model){
        commonDeviceAll(model);
        return "listdev";
    }

    @PostMapping("/updateListDevice/{id}")
    public String updateBrand(@PathVariable(value = "id",required = false) ListDevice Id,
                              @RequestParam("number") Long number,
                              @RequestParam("name") String name,
                              Model model){

        Id.setName(name);
        Id.setNumber(number);
        listDeviceRepo.save(Id);

        commonDeviceAll(model);
        return "listdev";
    }

    @PostMapping("/deleteListDevice/{id}")
    public String deleteDevice(@PathVariable(value = "id",required = false) Long id, Model model){
        listDeviceRepo.deleteById(id);

        commonDeviceAll(model);
        return "listdev";
    }

    @PostMapping("/createListDevice/")
    public String createListDevice (@RequestParam(name = "name") String name, @RequestParam(name="number") Long number, Model model){

        ListDevice listDevice=new ListDevice();
        listDevice.setName(name);
        listDevice.setNumber(number);

        if(!name.isEmpty() && number!=null) {
            listDeviceRepo.save(listDevice);
        }

        commonDeviceAll(model);

        return "index";
    }

    private void commonDeviceAll(Model model) {

        Iterable<ListDevice> listDevices=listDeviceRepo.findAll();
        Iterable<Device> devices=deviceRepo.findAll();
        model.addAttribute("listDevices",listDevices);
        model.addAttribute("devices",devices);
    }
}
