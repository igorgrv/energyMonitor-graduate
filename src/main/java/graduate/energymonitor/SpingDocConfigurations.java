package graduate.energymonitor;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Energy Monitor", version = "0.0.1", description = "Solution for tracking appliance and home electronic energy use through web APIs", license = @License(name = "MIT License", url = "https://github.com/igorgrv/energyMonitor-graduate")), servers = {
        @Server(url = "http://localhost:8080") })
public class SpingDocConfigurations {
}
