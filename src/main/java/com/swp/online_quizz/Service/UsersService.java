package com.swp.online_quizz.Service;

import com.swp.online_quizz.Entity.User;
import com.swp.online_quizz.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService implements IUsersService {
    @Autowired
    UsersRepository usersRepository;
    @Override
    public List<User> getAlList() {
        return this.usersRepository.findAll();
    }
    @Override
    public List<User> findIsactiveTeachers() {
        return usersRepository.findByRoleAndIsActive( "ROLE_TEACHER",false);
    }
    @Override
    public void toggleActive(Integer userId) {
        Optional<User> optionalUser = usersRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            Boolean currentIsActive = user.getIsActive();
            if (currentIsActive != null) {
                user.setIsActive(!currentIsActive);
            } else {
                // Handle case where isActive is null, set it to a default value
                user.setIsActive(true);
            }
            try {
                usersRepository.save(user);
            } catch (Exception e) {
                // Handle database save exception
                e.printStackTrace();
            }
        } else {
            // Handle case where no user found with the given userId
            System.out.println("No user found with userId: " + userId);
        }
    }

    @Override
    public List<User> getTeachers() {
        return usersRepository.findByRole("ROLE_TEACHER");
    }
    @Override
    public List<User> getStudent(){
        return usersRepository.findByRole("ROLE_STUDENT");
    }
    @Override
    public Boolean create(User users) {
        try {
            this.usersRepository.save(users);
            return true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return  false;
    }

    @Override
    public User findById(Integer userID) {
        return null;
    }

    @Override
    public Boolean update(User users) {
        return null;
    }

    @Override
    public Boolean delete(Integer userID) {
        return null;
    }
    @Override
    public User getUsersByID(Integer userID) {
        return usersRepository.getReferenceById(userID);
    }
}
