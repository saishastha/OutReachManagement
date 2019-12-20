export class AppConfig {

    static getConfig(): any {
        return {
            'api': {
                'getOutreachEvents': '/api/outreach/events',
                'getEventFeedbackDetails': '/api/outreach/feedbackdetails',
                'validateUserCredentials': '/api/user/login',
                'createNewUser': '/api/user/signup'
            }
        }
    }
}