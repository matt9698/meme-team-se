/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package property_tycoon.model;

/**
 *
 * @author adam
 */
public class FakeAction implements CardAction
{
    @Override
    public void execute(Player target)
    {
    }

    @Override
    public String getDescription()
    {
        return "Fake test action";
    }

    @Override
    public boolean isAlwaysExecutable()
    {
        return true;
    }

    @Override
    public boolean isExecutable()
    {
        return true;
    }
    
}
