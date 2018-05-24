/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tech.adrianohrl.identistry.control.dao.individuals;

import javax.persistence.EntityManager;
import tech.adrianohrl.dao.DAO;
import tech.adrianohrl.identistry.model.individuals.Channel;

/**
 *
 * @author adrianohrl
 */
public class ChannelDAO extends DAO<Channel, String> {

    public ChannelDAO(EntityManager em) {
        super(em, Channel.class);
    }

    @Override
    public boolean isRegistered(Channel channel) {
        return super.find(channel.getName()) != null;
    }
    
}
