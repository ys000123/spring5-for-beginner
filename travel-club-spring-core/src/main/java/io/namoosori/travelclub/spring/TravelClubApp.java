package io.namoosori.travelclub.spring;


import io.namoosori.travelclub.spring.aggregate.club.CommunityMember;
import io.namoosori.travelclub.spring.aggregate.club.TravelClub;
import io.namoosori.travelclub.spring.service.ClubService;
import io.namoosori.travelclub.spring.service.MemberService;
import io.namoosori.travelclub.spring.service.sdo.MemberCdo;
import io.namoosori.travelclub.spring.service.sdo.TravelClubCdo;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;
import java.util.Date;

// entrypoint
public class TravelClubApp {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        String [] beanNames = context.getBeanDefinitionNames();
        System.out.println(Arrays.toString(beanNames)); // 등록된 bean 이름 출력
        System.out.println("-----------------------------------------------------------");

        TravelClubCdo clubCdo = new TravelClubCdo("TravelClub","Test TravelClub");   // Create domain object
        ClubService clubService = context.getBean("clubService", ClubService.class);

        String clubId = clubService.registerClub(clubCdo);

        TravelClub foundedClub = clubService.findClubById(clubId);
        System.out.println("Club name" + foundedClub.getName());
        System.out.println("Club intro" + foundedClub.getIntro());
        System.out.println("Club foundation Time" + new Date(foundedClub.getFoundationTime()));
        System.out.println("-----------------------------------------------------------");

        MemberService memberService = context.getBean("memberServiceLogic", MemberService.class);
        // Bean으로 가져온다

        String memberId = memberService.registerMember(
                new MemberCdo(
                        "test@nextree.io",
                        "kim",
                        "Test Member",
                        "010-0000-0000",
                        "2020.01.01"));
        CommunityMember foundedMember  = memberService.findMemberById(memberId);
        System.out.println(foundedMember.toString());

    }

}
