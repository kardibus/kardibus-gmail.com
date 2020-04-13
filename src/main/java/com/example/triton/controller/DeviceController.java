package com.example.triton.controller;

import com.example.triton.domain.Device;
import com.example.triton.domain.ListDevice;
import com.example.triton.domain.User;
import com.example.triton.repository.DeviceRepo;
import com.example.triton.repository.ListDeviceRepo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Controller
public class DeviceController {

    private final DeviceRepo deviceRepo;
    private final ListDeviceRepo listDeviceRepo;

    public DeviceController(DeviceRepo deviceRepo, ListDeviceRepo listDeviceRepo) {
        this.deviceRepo = deviceRepo;
        this.listDeviceRepo = listDeviceRepo;
    }

    @GetMapping("/index")
    public String deviceAll(@AuthenticationPrincipal User user, Model model){
        commonDeviceAll(model,user);
        return "index";
    }

    @GetMapping("/")
    public String deviceIndexAll(@AuthenticationPrincipal User user,Model model){

        commonDeviceAll(model,user);

        return "redirect:/index";
    }

    @GetMapping("/dev")
    public String dev(@AuthenticationPrincipal User user,Model model){
        commonDeviceAll(model,user);
        return "dev";
    }

    @PostMapping("/updateDevice/{id}")
    public String updateBrand(@AuthenticationPrincipal User user,@PathVariable(value = "id",required = false) Device Id,
                              @RequestParam("quantity") Long quantity,
                              @RequestParam("listDevice") ListDevice listDevice,
                              Model model){

            Id.setQuantity(quantity);
            Id.setListDevice(listDevice);
            Id.setDate(new Date());
            deviceRepo.save(Id);


        commonDeviceAll(model,user);

        return "dev";
    }

    @PostMapping("/deleteDevice/{id}")
    public String deleteDevice(@AuthenticationPrincipal User user,@PathVariable(value = "id",required = false) Long id, Model model){

        deviceRepo.deleteById(id);

       commonDeviceAll(model,user);

       return "dev";
    }

    @PostMapping("/createDevice/")
    public String createDeviceIndex (@AuthenticationPrincipal User user, @RequestParam(name = "quantity",required = false) Long quantity, @RequestParam(name = "listDevice",required = false) ListDevice id, Model model){

        if (quantity!=null) {
            Device device = new Device();
            device.setQuantity(quantity);
            device.setListDevice(id);
            device.setDate(new Date());
            device.setAuthor(user);
            deviceRepo.save(device);
        }

        commonDeviceAll(model,user);

        return "index";
    }

    private void commonDeviceAll(Model model,User user) {
        if(user!=null) {
            Iterable<ListDevice> listDevices = listDeviceRepo.findAll();
            Iterable<Device> devices = deviceRepo.findAllByAuthor(user.getId());
            model.addAttribute("listDevices", listDevices);
            model.addAttribute("devices", devices);
        }
    }

    @PostMapping("/search")
    public String search(@AuthenticationPrincipal User user,@RequestParam(name ="search",defaultValue = "0000-00-00") String search, @RequestParam(name = "day",defaultValue = "0") String day, @RequestParam(name = "month",defaultValue = "0") String month, @RequestParam(name = "year",defaultValue = "0") String year, Model model) throws ParseException {

        DateFormat format=new SimpleDateFormat("yyyy-mm-dd");
        Date date=format.parse(search);
        String dateFormat=format.format(date);
        String[] str=dateFormat.split("-");

         if(day.equals("1") && !month.equals("2")) {

             Iterable<ListDevice> listDevices = listDeviceRepo.findAll();
             Iterable<Device> devices = deviceRepo.findByDay("%"+str[2]+"."+str[1]+"."+"%",user.getId());

             model.addAttribute("listDevices", listDevices);
             model.addAttribute("devices", devices);
         }else if(month.equals("2")){

             Iterable<ListDevice> listDevices = listDeviceRepo.findAll();
             Iterable<Device> devices = deviceRepo.findByMonth(str[0]+"-"+str[1]+"-"+"01",dateFormat,user.getId());

             model.addAttribute("listDevices", listDevices);
             model.addAttribute("devices", devices);
         }else{
             commonDeviceAll(model,user);
         }
        return "index";
    }
}
