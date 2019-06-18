package io.bitmax.api.rest.messages.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class CancelOrderResponse {

    @JsonProperty("code")
    private int code;

    @JsonProperty("status")
    private String status;

    @JsonProperty("email")
    private String email;

    @JsonProperty("data")
    private Data data;

    class Data {
        @JsonProperty("action")
        private String action;

        @JsonProperty("coid")
        private String coid;

        @JsonProperty("success")
        private boolean success;

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }

        public String getCoid() {
            return coid;
        }

        public void setCoid(String coid) {
            this.coid = coid;
        }

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        @Override
        public String toString() {
            return "Data:\n\taction: " + action +
                    "\n\tcoid: " + coid +
                    "\n\tsuccess: " + success;
        }
    }

    @Override
    public String toString() {
        return "CancelOrderResponse:\n\tcode: " + code +
                "\n\tstatus: " + status +
                "\n\temail: " + email +
                "\n\tdata: " + data;
    }
}
