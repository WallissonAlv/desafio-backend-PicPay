package br.com.wallissonalves.picpay.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.wallissonalves.picpay.entity.Transfer;

public interface TransferRepository extends JpaRepository<Transfer, UUID> {

}
