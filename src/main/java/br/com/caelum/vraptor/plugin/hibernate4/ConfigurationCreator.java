package br.com.caelum.vraptor.plugin.hibernate4;

import java.net.URL;

import javax.annotation.PostConstruct;

import org.hibernate.cfg.Configuration;

import br.com.caelum.vraptor.environment.Environment;
import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.ComponentFactory;
import br.com.caelum.vraptor.ioc.Container;

@Component
@ApplicationScoped
public class ConfigurationCreator
	implements ComponentFactory<Configuration> {

	private Configuration cfg;
	private Container container;

	public ConfigurationCreator(Container container) {
		this.container = container;
	}

	/**
	 * Create a new instance for {@link Configuration}, and after call the
	 * {@link ConfigurationCreator#configureExtras()} method, that you can override to configure extra guys.
	 * If vraptor-environment is available on classpath, this method will use then to locate hibernate cfg
	 * file.
	 */
	@PostConstruct
	public void create() {
		cfg = new Configuration().configure(getHibernateCfgLocation());
		configureExtras();
	}

	protected URL getHibernateCfgLocation() {
		if (isEnvironmentAvailable()) {
			Environment env = container.instanceFor(Environment.class);
			return env.getResource(getHibernateCfgName());
		}

		return getClass().getResource(getHibernateCfgName());
	}

	protected String getHibernateCfgName() {
		return "/hibernate.cfg.xml";
	}

	protected boolean isEnvironmentAvailable() {
		return false;
//		try {
//			Class.forName("br.com.caelum.vraptor.environment.Environment");
//			return true;
//		} catch (ClassNotFoundException e) {
//			return false;
//		}
	}

	/**
	 * This method can override if you want to configure more things.
	 */
	public void configureExtras() {

	}

	public Configuration getInstance() {
		return cfg;
	}
}
