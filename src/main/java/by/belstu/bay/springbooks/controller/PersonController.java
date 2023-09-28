package by.belstu.bay.springbooks.controller;
import by.belstu.bay.springbooks.forms.PersonForm;
import by.belstu.bay.springbooks.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import java.util.ArrayList;
import java.util.List;
@Slf4j
@Controller
public class PersonController {
    private static List<Person> people = new ArrayList<Person>();
    static {
        people.add(new Person("Bay Igor Olegovich", "2002-12-22 [19]"));
    }
    @Value("${welcome.message}")
    private String message;
    @Value("${error.message}")
    private String errorMessage;
    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public ModelAndView index(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        model.addAttribute("message", message);
        log.info("/index was called");
        return modelAndView;
    }
    @RequestMapping(value = {"/allPersons"}, method = RequestMethod.GET)
    public ModelAndView personList(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("personList");
        model.addAttribute("personL", people);
        return modelAndView;
    }

    @RequestMapping(value = {"/addPerson"}, method = RequestMethod.GET)
    public ModelAndView showAddPersonPage(Model model) {
        ModelAndView modelAndView = new ModelAndView("addPerson");
        PersonForm personForm = new PersonForm();
        model.addAttribute("personForm", personForm);
        return modelAndView;
    }
    // @PostMapping("/addbook")
    //GetMapping("/")
    @RequestMapping(value = {"/addPerson"}, method = RequestMethod.POST)
    public ModelAndView savePerson(Model model, @ModelAttribute("personForm") PersonForm personForm) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("personList");
        String title = personForm.getName();
        String author = personForm.getAge();
        if (title != null && title.length() > 0 //
                && author != null && author.length() > 0) {
            Person newPerson = new Person(title, author);
            people.add(newPerson);
            model.addAttribute("personL", people);
            return modelAndView;
        }
        model.addAttribute("errorMessage", errorMessage);
        modelAndView.setViewName("addPerson");
        return modelAndView;
    }
    @RequestMapping(value = {"/removePerson"}, method = RequestMethod.GET)
    public ModelAndView showRemovePersonPage(Model model){
        ModelAndView modelAndView = new ModelAndView("removePerson");
        PersonForm personForm = new PersonForm();
        model.addAttribute("personForm", personForm);
        return modelAndView;
    }
    @RequestMapping(value = {"/removePerson"}, method = RequestMethod.POST)
    public ModelAndView removePerson(Model model, @ModelAttribute("personForm") PersonForm personForm){
        ModelAndView modelAndView = new ModelAndView("personList");
        String title = personForm.getName();
        if (title != null && title.length() > 0){
            for (Person bf : people){
                if (bf.getName().equals(title)){
                    people.remove(bf);
                    model.addAttribute("personL", people);
                    return modelAndView;
                }
            }
        }
        model.addAttribute("errorMessage", errorMessage);
        modelAndView.setViewName("removePerson");
        return modelAndView;
    }
    @RequestMapping(value = {"/editPerson"}, method = RequestMethod.GET)
    public ModelAndView showEditPersonPage(Model model){
        ModelAndView modelAndView = new ModelAndView("editPerson");
        Person oldPerson = new Person();
        PersonForm personForm = new PersonForm();
        model.addAttribute("personForm", personForm);
        model.addAttribute("oldPerson", oldPerson);
        return modelAndView;
    }
    @RequestMapping(value = {"/editPerson"}, method = RequestMethod.POST)
    public ModelAndView editPerson(Model model, @ModelAttribute("personForm") PersonForm personForm){
        ModelAndView modelAndView = new ModelAndView("personList");
        String oldname = personForm.getEditField();
        String newName = personForm.getName();
        String newProfession = personForm.getAge();
        if (oldname != null && oldname.length() > 0 && newName != null && newName.length() > 0 && newProfession != null && newProfession.length() > 0){
            for (Person b : people){
                if (b.getName().equals(oldname)){
                    b.setName(newName);
                    b.setAge(newProfession);
                    model.addAttribute("personL", people);
                    return modelAndView;
                }
            }
        }
        model.addAttribute("errorMessage", errorMessage);
        modelAndView.setViewName("editPerson");
        return modelAndView;
    }

}
