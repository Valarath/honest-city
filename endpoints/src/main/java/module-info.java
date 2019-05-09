module cz.honestcity.endpoints {
    requires spring.web;
    requires spring.beans;
    requires cz.honestcity.service;
    requires lombok;
    requires spring.context;

    exports cz.honestcity.endpoints.subject;
    exports cz.honestcity.endpoints.suggestion;
    exports cz.honestcity.endpoints.user;
    exports cz.honestcity.endpoints.vote;
}