package reponsemodel;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateJobApiResponseModal {

	private String message;
    private CreateJobDataModel data;
}
