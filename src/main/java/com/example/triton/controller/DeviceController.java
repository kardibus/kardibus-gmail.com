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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;


@Controller
public class DeviceController {

    @Autowired
    private DeviceRepo deviceRepo;
    @Autowired
    private ListDeviceRepo listDeviceRepo;

    @GetMapping("/")
    public String deviceIndexAll(Model model){

        commonDeviceAll(model);

        return "index";
    }

    @GetMapping("/dev")
    public String dev(Model model){
        commonDeviceAll(model);
        return "dev";
    }

    @PostMapping("/updateDevice/{id}")
    public String updateBrand(@PathVariable(value = "id",required = false) Device Id,
                              @RequestParam("quantity") Long quantity,
                              @RequestParam("listDevice") ListDevice listDevice,
                              Model model){

        Id.setQuantity(quantity);
        Id.setListDevice(listDevice);
        Id.setDate(new Date());
        deviceRepo.save(Id);

        commonDeviceAll(model);
        return "dev";
    }

    @PostMapping("/deleteDevice/{id}")
    public String deleteDevice(@PathVariable(value = "id",required = false) Long id, Model model){
        deviceRepo.deleteById(id);

       commonDeviceAll(model);
       return "dev";
    }

    @PostMapping("/createDevice/")
    public String createDeviceIndex (@RequestParam(name = "quantity",required = false) Long quantity,@RequestParam(name = "listDevice",required = false) ListDevice id, Model model){


        if (quantity!=null) {
            Device device = new Device();
            device.setQuantity(quantity);
            device.setListDevice(id);
            device.setDate(new Date());
            deviceRepo.save(device);
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

    @PostMapping("/search")
    public String search(@RequestParam(name ="search") String search, @RequestParam(name = "day") Boolean day,@RequestParam(name = "month",required = false) Boolean month,@RequestParam(name = "year",required = false) Boolean year, Model model) throws ParseException {
         if(day) {
             String[] str = search.split("-");

             Iterable<ListDevice> listDevices = listDeviceRepo.findAll();
             Iterable<Device> devices = deviceRepo.findBy("%" + str[2] + "%");

             model.addAttribute("listDevices", listDevices);
             model.addAttribute("devices", devices);
         }else{
             commonDeviceAll(model);
         }
        return "index";
    }
}
