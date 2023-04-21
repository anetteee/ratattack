package com.ratattack.game.model.ecs.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.ratattack.game.model.ecs.components.UserComponent;

public class UserSystem extends IteratingSystem {

    private static final Family userComponentFamily = Family.all(UserComponent.class).get();
    private ComponentMapper<UserComponent> userMapper;
    public UserSystem() {
        super(userComponentFamily);
        userMapper = ComponentMapper.getFor(UserComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        UserComponent userComponent = userMapper.get(entity);

        //Update highscore
        //Kanskje update balance? kommer an på hvordan vi implementerer hvordan den vet om balansen har økt idk
    }

    public void setUsername(Entity userEntity, String username) {
        userMapper.get(userEntity).name = username;
    }

    public String getUsername(Entity userEntity) {
        return userMapper.get(userEntity).name;
    }

    public void setScore(Entity userEntity, int score) {
        userMapper.get(userEntity).score = score;
    }

    public int getScore(Entity userEntity) {
        return userMapper.get(userEntity).score;
    }

    public void setCurrency(Entity userEntity, int currency) {
        userMapper.get(userEntity).balance = currency;
    }

    public int getCurrency(Entity userEntity) {
        return userMapper.get(userEntity).balance;
    }
}
