class ServiceLocator:
    _instance = None
    _services = {}

    @classmethod
    def get_instance(cls):
        if cls._instance is None:
            cls._instance = ServiceLocator()

        return cls._instance

    def register(self, service_type, impl):
        self._services[service_type] = impl

    def get(self, service_type):
        if service_type not in self._services:
            raise KeyError(f"Service {service_type} not registered")

        return self._services[service_type]
