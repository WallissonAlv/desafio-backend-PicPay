package br.com.wallissonalves.picpay.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.wallissonalves.picpay.entity.WalletType;

public interface WalletTypeRepository extends JpaRepository<WalletType, Long>{

}
