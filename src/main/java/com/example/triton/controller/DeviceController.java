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

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


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
    public String search(@RequestParam(name ="search",defaultValue = "0000-00-00") String search, @RequestParam(name = "day",defaultValue = "0") String day, @RequestParam(name = "month",defaultValue = "0") String month, @RequestParam(name = "year",defaultValue = "0") String year, Model model) throws ParseException {

        DateFormat format=new SimpleDateFormat("yyyy-mm-dd");
        Date date=format.parse(search);
        String dateFormat=format.format(date);
        String[] str=dateFormat.split("-");

         if(day.equals("1") && !month.equals("2")) {

             Iterable<ListDevice> listDevices = listDeviceRepo.findAll();
             Iterable<Device> devices = deviceRepo.findByDay("%"+str[2]+"."+str[1]+"."+"%");

             model.addAttribute("listDevices", listDevices);
             model.addAttribute("devices", devices);
         }else if(month.equals("2")){

             Iterable<ListDevice> listDevices = listDeviceRepo.findAll();
             Iterable<Device> devices = deviceRepo.findByMonth(str[2]+"-"+str[1]+"-"+"01",search);

             model.addAttribute("listDevices", listDevices);
             model.addAttribute("devices", devices);
         }else{
             commonDeviceAll(model);
         }
        return "index";
    }
}
