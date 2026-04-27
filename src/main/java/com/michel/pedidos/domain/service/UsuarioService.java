package com.michel.pedidos.domain.service;

import com.michel.pedidos.domain.exception.NegocioException;
import com.michel.pedidos.domain.model.Usuario;
import com.michel.pedidos.domain.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class UsuarioService {

   private final UsuarioRepository usuarioRepository;

    public List<Usuario> listar () {
        return usuarioRepository.findAll();
    }

    public Usuario buscar (Long usuarioId) {
        return usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new NegocioException("Usuario nao encontrado"));
    }

    @Transactional
    public void excluir (Long usuarioId) {
        usuarioRepository.deleteById(usuarioId);
    }

    @Transactional
    public Usuario adicionar (Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public Usuario atualizar(Long usuarioId, Usuario usuario) {
        Usuario usuarioAtual = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new NegocioException("Usuário não encontrado"));

        boolean cpfJaExiste = usuarioRepository.findByCpf(usuario.getCpf())
                        .filter(u -> u.getId() != null && !u.getId().equals(usuarioId))
                                .isPresent();
        if (cpfJaExiste) {
            throw new NegocioException("Cpf ja cadastrado");
        }

        usuarioAtual.setNome(usuario.getNome());
        usuarioAtual.setEmail(usuario.getEmail());
        usuarioAtual.setCpf(usuario.getCpf());

        return usuarioRepository.save(usuarioAtual);
    }

}
