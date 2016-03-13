package be.g00glen00b.controller;

import be.g00glen00b.dto.MessageDTO;
import be.g00glen00b.dto.TaskDTO;
import be.g00glen00b.service.TaskNotFoundException;
import be.g00glen00b.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    @Autowired
    private TaskService service;
    @Autowired
    private MessageSource msgSource;

    @RequestMapping(method = RequestMethod.GET)
    public List<TaskDTO> findAll() {
        return service.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public TaskDTO save(@Valid @RequestBody TaskDTO dto) {
        return service.save(dto);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public TaskDTO update(@PathVariable Long id, @Valid @RequestBody TaskDTO dto) {
        return service.update(id, dto);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @ExceptionHandler(TaskNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public MessageDTO handleTaskNotFound(TaskNotFoundException ex) {
        final String message = msgSource.getMessage("error.task.not-found", null, LocaleContextHolder.getLocale());
        return new MessageDTO("ERR_INV_OBJECT", message);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public MessageDTO handleTaskValidationException(MethodArgumentNotValidException ex) {
        final ObjectError globalError = ex.getBindingResult().getGlobalError();
        final FieldError fieldError = ex.getBindingResult().getFieldError();
        final Locale locale = LocaleContextHolder.getLocale();
        if (fieldError == null) {
            return new MessageDTO("ERR_INV_OBJECT", msgSource.getMessage(globalError.getDefaultMessage(), null, locale));
        } else {
            return new MessageDTO("ERR_INV_OBJECT", msgSource.getMessage(fieldError.getDefaultMessage(), null, locale));
        }
    }
}
