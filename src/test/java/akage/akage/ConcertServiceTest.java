package akage.akage;

import com.example.akage.domain.Concert;
import com.example.akage.domain.Genre;
import com.example.akage.dto.ConcertInput;
import com.example.akage.dto.ConcertResponse;
import com.example.akage.repository.ConcertRepository;
import com.example.akage.service.ConcertService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConcertServiceTest {

    @Mock
    private ConcertRepository concertRepository;

    @InjectMocks
    private ConcertService concertService;

    @Test
    void createConcert_shouldSaveAndReturnConcertResponse() {
        // 테스트용 ConcertInput 객체 생성
        ConcertInput input = ConcertInput.builder()
                .title("Test Concert")
                .place("Seoul")
                .locate(com.example.akage.domain.Locate.SEOUL)
                .address("123 Test St")
                .startTime(LocalDateTime.of(2025, 8, 10, 19, 0))
                .ticketTime(LocalDateTime.of(2025, 7, 1, 10, 0))
                .ticketSite("Test Ticket Site")
                .ticketLink("http://testticket.com")
                .poster("http://testposter.com/poster.jpg")
                .genre(Genre.FESTIVAL)
                .artist(java.util.List.of("Test Artist"))
                .price(java.util.Map.of("VIP", 100000))
                .build();

        // 저장될 Concert 객체 준비
        Concert savedConcert = Concert.builder()
                .id(UUID.randomUUID())
                .title(input.getTitle())
                .place(input.getPlace())
                .locate(input.getLocate())
                .address(input.getAddress())
                .startTime(input.getStartTime())
                .ticketTime(input.getTicketTime())
                .ticketSite(input.getTicketSite())
                .ticketLink(input.getTicketLink())
                .poster(input.getPoster())
                .genre(input.getGenre())
                .artist(input.getArtist())
                .price(input.getPrice())
                .approved(false)
                .build();

        when(concertRepository.save(any(Concert.class))).thenReturn(savedConcert);

        // 서비스 메서드 실행
        ConcertResponse response = concertService.createConcert(input);

        // 결과 검증
        assertNotNull(response);
        assertEquals(savedConcert.getId(), response.getId());
        assertEquals(savedConcert.getTitle(), response.getTitle());

        verify(concertRepository, times(1)).save(any(Concert.class));
    }
}
