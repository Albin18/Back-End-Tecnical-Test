package com.test.backendapirest.controller;

import com.test.backendapirest.model.dto.UsuarioDtoRequest;
import com.test.backendapirest.model.dto.UsuarioDtoResponse;
import com.test.backendapirest.model.entity.Usuario;
import com.test.backendapirest.model.service.IUsuarioService;
import com.test.backendapirest.util.RegexValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UsuarioRestController {
    @Autowired
    private IUsuarioService usuarioService;
    private RegexValidation regex = new RegexValidation();
    @GetMapping("/usuarios")
    public List<UsuarioDtoResponse> index(){
        List<Usuario> users = usuarioService.findAll();
        List<UsuarioDtoResponse> dtoResponses = new ArrayList<>();
        users.stream().forEach(user -> {
            user.setPhones(new ArrayList<>());
            dtoResponses.add(usuarioService.serializar(user));
        });
        return dtoResponses;
    }


    @GetMapping("/usuarios/{email}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> show(@PathVariable String email){
        Map<String, Object> response = new HashMap<>();
        try{
            Usuario usuario = usuarioService.findByEmail(email);
            usuario.setPhones(new ArrayList<>());
            UsuarioDtoResponse userDto = usuarioService.serializar(usuario);
            if(userDto == null){
                response.put("mensaje", "El cliente email: ".concat(email.concat(" No existe en la base de datos!")));
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
            }
            response.put("usuario", userDto);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        } catch(DataAccessException e){
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/usuarios")
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody UsuarioDtoRequest usuariodt){

        Map<String,Object> response = new HashMap<>();
    try {

        if(!regex.patternEmailValidation(usuariodt.getEmail())){
            response.put("mensaje", "Formato de correo invalido");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        if(!regex.patternPasswordValidation(usuariodt.getPassword())){
            response.put("mensaje", "Formato de contraseña invalido");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        UsuarioDtoResponse usuarioNew = usuarioService.save(usuariodt);
        response.put("mensaje", "El usuario ha sido creado con exito!");
        response.put("usuario", usuarioNew);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

    } catch (DataAccessException e){

        if(e.getMessage().contains("constraint")){
            response.put("mensaje", "Este correo ya existe");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        response.put("mensaje", "Error al realizar la creacion a la base de datos!");
        response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/usuarios/{email}")
    public ResponseEntity<?> update(@RequestBody UsuarioDtoRequest usuariodt, @PathVariable String email){
        Map<String,Object> response = new HashMap<>();
        try{
            Usuario usuarioActual = usuarioService.findByEmail(email);

            if(!regex.patternPasswordValidation(usuariodt.getPassword())){
                response.put("mensaje", "contraseña invalida");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
            }
            UsuarioDtoResponse usuario = usuarioService.update(usuarioActual, usuariodt);
            response.put("mensaje","El usuario ha sido actualizado con exito!" );
            response.put("usuario", usuario);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
        } catch(DataAccessException e){
            response.put("mensaje", "Error al actualizar el usuario en la base de datos");
            response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch(Exception e){
            response.put("mensaje", "Error al actualizar el usuario en la base de datos");
            response.put("Error", e.getMessage().concat(": ").concat(e.getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @DeleteMapping("/usuarios/{email}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> delete(@PathVariable String email) {
        Map<String, Object> response = new HashMap<>();
        try {
            var object = usuarioService.delete(email) == 1 ? response.put("mensaje", "El usuario se elimino con exito!")
                    : response.put("error", "El usuario que intenta eliminar no existe!");
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar el usuario de la base de datos!");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
