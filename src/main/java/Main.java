import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import dto.Car;
import dto.USer;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class Main {


//‘-제이슨 포맷 이쁘게
//    https://jsonformatter.curiousconcept.com/

//
//
//    ‘-object mapper란 텍스트 형태의 JSON을 object로 변경해 주거나 object를 텍스트 형태의 JSON으로 변경해 주는 것을 의미한다. 예를 들면 컨트롤러에 요청이 오면 JSON을 object로 변경을 했고, 응답으로 보내면 자동으로 object를 json으로 변경했다.
//
//    Controller 외에 내가 따로 작업을 하거나, 객체를 JSON으로 바꿔야한다면, 위와 같이 var objectMapper를 생성해서 writeValueAsString이나 readValue를 활용하여 JSON <-> object 간의 변환이 가능하다. 이 외의 objectMapper에 추가하는 방식도 있다는 점을 이해하자.





    public static void main(String[] args) throws JsonProcessingException {
        System.out.println("main");

        ObjectMapper objectMapper = new ObjectMapper();

        USer user = new USer();
        user.setName("홍길동");
        user.setAge(10);

        Car car1 = new Car();
        car1.setName("K5");
        car1.setCar_number("11rk 1111");
        car1.setType("sedatn");

        Car car2 = new Car();
        car2.setName("Q5");
        car2.setCar_number("22rk 2222");
        car2.setType("suv");


        List<Car> carList = Arrays.asList(car1,car2);

//        ‘-asList: 리턴시 새로운 어레이리스트 반환시켜줌
//        List<Car> carList = Arrays.asList(car1,car2);
//        @SafeVarargs
//        @SuppressWarnings("varargs")
//        public static <T> List<T> asList(T... a) {
//            return new ArrayList<>(a);
//        }


//‘-object mapper란 텍스트 형태의 JSON을 object로 변경해 주거나 object를 텍스트 형태의 JSON으로 변경해 주는 것을 의미한다. 예를 들면 컨트롤러에 요청이 오면 JSON을 object로 변경을 했고, 응답으로 보내면 자동으로 object를 json으로 변경했다.



                user.setCars(carList);

        //System.out.println(user);

        String json = objectMapper.writeValueAsString(user);
        System.out.println(json);

        JsonNode jsonNode = objectMapper.readTree(json);
        String _name = jsonNode.get("name").asText();
        int _age = jsonNode.get("age").asInt();
        System.out.println("_name : " + _name);
        System.out.println("_age : " + _age);

//
//        String _list = jsonNode.get("cars").asText();
//        System.out.println("_list : " + _list);


        JsonNode cars = jsonNode.get("cars");
        ArrayNode arrayNode = (ArrayNode)cars;


        List<Car> _cars = objectMapper.convertValue(arrayNode, new TypeReference<List<Car>>() {});
        System.out.println("_Cars : " +  _cars);



        //json data upade!!
        ObjectNode objectNode   =   (ObjectNode)jsonNode;
        objectNode.put("name" , "steve");
        objectNode.put("age",20);
        System.out.println(objectNode.toPrettyString());
    }
}
