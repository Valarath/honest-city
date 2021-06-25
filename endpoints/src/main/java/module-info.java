module cz.honestcity.endpoints {
    requires spring.web;
    requires spring.beans;
    requires cz.honestcity.service;
    requires lombok;
    requires spring.context;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.core;

    exports cz.honestcity.endpoints.subject;
    exports cz.honestcity.endpoints.suggestion;
    exports cz.honestcity.endpoints.user;
    exports cz.honestcity.endpoints.vote;
    exports cz.honestcity.endpoints.authority;
    exports cz.honestcity.endpoints.login;
}