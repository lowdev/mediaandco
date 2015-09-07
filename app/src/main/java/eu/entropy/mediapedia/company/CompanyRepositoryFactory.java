package eu.entropy.mediapedia.company;

public class CompanyRepositoryFactory {
    private static CompanyRepository companyRepository;

    public static CompanyRepository get() {
        if (null == companyRepository) {
            companyRepository = new CompanyRepository();
        }

        return companyRepository;
    }
}
