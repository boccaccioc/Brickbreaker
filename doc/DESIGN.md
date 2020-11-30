# Simulation Design Final
### Names

## Team Roles and Responsibilities

 * Team Member #1

Colin Bocccaccio : The whole thing

 * Team Member #2

Liam Idrovo: Contributed exclusively to the early planning phase of the project. 


## Design goals

One goal that took a long time to complete was creating different classes for different objects such as the ball and bricks.

Class hierarchy for different types of bricks.

#### What Features are Easy to Add

Features that modify stats such as the speed, size, and color of an object when certain criteria are met.

Adding new levels.

## High-level Design

Each different object that has collision and a node in the root all are their own class, with there being multiple classes extending brick for different types.

#### Core Classes

Game, Ball, Brick, Paddle

## Assumptions that Affect the Design

It is assumed that level files take the format of 8 characters on each row, with only characters 0-7, "E", "B", "S". Also no more than 4 rows in each level file.

#### Features Affected by Assumptions

Code that creates the levels off of the text file is build with the expectation that the level file text follows the statedd criteria.

## New Features HowTo

To create a new brick type, simple make a new class that extends Brick and has a constructor that calls super and an override of the updateStatus method.

#### Easy to Add Features

A modified paddle that makes the ball bounce off of it much faster.

Balls that break through multiple layers of the colored bricks.

#### Other Features not yet Done

A block that shoots down a missile when broken that makes the player lose a life if it hits the paddle.

Brick that shoots out multiple balls in different directions when broken.