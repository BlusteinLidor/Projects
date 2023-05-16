from pickletools import UP_TO_NEWLINE
from tkinter import CENTER
from turtle import up
import pygame
from sys import exit
from random import randint

def obs_movement(obs_list):
    if obs_list:
        for obs_rect in obs_list:
            if obs_rect.top < 200:
                obs_up_rect.x -= 5
                screen.blit(obs_up_scaled, obs_up_rect)
                
            else:
                screen.blit(obs_down_scaled, obs_down_rect)
                obs_down_rect.x -= 5
        obs_list = [obs_rect for obs_rect in obs_list if obs_rect.x > 100]
        return obs_list
    else:
        return []

def collision(bird, obs):
    if obs:
        for obs_rect in obs:
            if bird.colliderect(obs_rect):
                return False
    return True


#game initializing
pygame.init()
screen = pygame.display.set_mode((800, 400))
pygame.display.set_caption('Flappy Bird')
clock = pygame.time.Clock()
game_active = True
gravity = 0
obs_rect_list = []

#text components
gameover_font = pygame.font.Font(None, 180)
time_font = pygame.font.Font(None, 50)
gameover_surface = gameover_font.render("GAME OVER", True, "Red")
gameover_rect = gameover_surface.get_rect(center = (400, 200))

#bird surfaces
bird_surf = pygame.image.load("FlappyBird/bird.png").convert_alpha()
bird_scaled = pygame.transform.scale(bird_surf, (100,100))
bird_forward = bird_scaled
bird_for_rect = bird_forward.get_rect(topleft = (200,150))
bird_upward = pygame.transform.rotate(bird_scaled, 45)
bird_up_rect = bird_upward.get_rect(topleft = (200,0))
bird_downward = pygame.transform.rotate(bird_scaled, -45) 
bird_down_rect = bird_downward.get_rect(topleft = (200,0))

#obstacle surfaces
#obs_surf = pygame.image.load("FlappyBird/obstacle.png").convert_alpha()
obs_up_surf = pygame.image.load("FlappyBird/obstacle_up.png").convert_alpha()
obs_down_surf = pygame.image.load("FlappyBird/obstacle_down.png").convert_alpha()
#obs_scaled = pygame.transform.scale(obs_surf, (100,501))
obs_up_scaled = pygame.transform.scale(obs_up_surf, (50,100))
obs_down_scaled = pygame.transform.scale(obs_down_surf, (50,100))
#obs_rect = obs_scaled.get_rect(topleft = (700,0))
obs_up_rect = obs_up_scaled.get_rect(topleft = (700,0))
obs_down_rect = obs_down_scaled.get_rect(bottomleft = (700,400))

#background surfaces
bg_surf = pygame.image.load("FlappyBird/background.png")
bg_scaled = pygame.transform.scale(bg_surf, (800, 400))

#timer
obs_timer = pygame.USEREVENT + 1
pygame.time.set_timer(obs_timer, 1100)


#game loop
while True:

    screen.blit(bg_scaled, (0,0))
    screen.blit(bird_forward, bird_for_rect)
    
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            pygame.quit()
            exit()

        if event.type == obs_timer and game_active:
            print("test")
            obs_rect_list.append(obs_up_scaled.get_rect(topleft = (800,randint(-100, 0))))
            obs_rect_list.append(obs_down_scaled.get_rect(bottomleft = (800,randint(400, 500))))


    if bird_for_rect.y < -50 or bird_for_rect.y > 350:
        game_active = False

    if game_active:
        gravity += 0.2
        bird_for_rect.y += gravity
        keys = pygame.key.get_pressed()
        if keys[pygame.K_SPACE]:
            gravity -= 1.5
        if gravity > 2:
            bird_forward = bird_downward
        elif gravity < 0:
            bird_forward = bird_upward
        obs_rect_list = obs_movement(obs_rect_list)
        game_active = collision(bird_for_rect,obs_rect_list)
    else:
        screen.blit(gameover_surface, gameover_rect)


    pygame.display.update()
    clock.tick(60)